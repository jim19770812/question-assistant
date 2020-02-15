package com.common.utils;

import java.util.*;
import java.io.*;

public class IniUtil {
    private Properties globalProperties;
    private Map<String,Properties> properties;

    enum ParseState {
        NORMAL,
        ESCAPE,
        ESC_CRNL,
        COMMENT
    }

    public IniUtil() {
        globalProperties = new Properties();
        properties = new HashMap<String,Properties>();
    }

    /**
     * 从流中加载INI信息
     */
    public void load(InputStream in) throws IOException {
        int bufSize = 4096;
        byte[] buffer = new byte[bufSize];
        int n = in.read(buffer, 0, bufSize);

        ParseState state = ParseState.NORMAL;
        boolean section_open = false;
        String current_section = null;
        String key = null, value = null;
        StringBuilder sb = new StringBuilder();
        while (n >= 0) {
            for (int i = 0; i < n; i++) {
                char c = (char) buffer[i];

                if (state == ParseState.COMMENT) { // comment, skip to end of line
                    if ((c == '\r') ||(c == '\n')) {
                        state = ParseState.NORMAL;
                    }
                    else {
                        continue;
                    }
                }

                if (state == ParseState.ESCAPE) {
                    sb.append(c);
                    if (c == '\r') {
                        // if the EOL is \r\n, \ escapes both chars
                        state = ParseState.ESC_CRNL; 
                    }
                    else {
                        state = ParseState.NORMAL;
                    }
                    continue;
                }

                switch (c) {
                    case '[': // run section
                        sb = new StringBuilder();
                        section_open = true;
                        break;
                    
                    case ']': // end section
                        if (section_open) {
                            current_section = sb.toString().trim();
                            sb = new StringBuilder();
                            properties.put(current_section, new Properties());
                            section_open = false;
                        }
                        else {
                            sb.append(c);
                        }
                        break;

//                    case '\\': // escape char, take the next char as is
//                        state = ParseState.ESCAPE;
//                        break;

                    case '#': 
                    case ';': 
                        state = ParseState.COMMENT;
                        break;

                    case '=': // assignment operator
                    case ':':
                        if (key == null) {
                            key = sb.toString().trim();
                            sb = new StringBuilder();
                        }
                        else {
                            sb.append(c);
                        }
                        break;

                    case '\r':
                    case '\n':
                        if ((state == ParseState.ESC_CRNL) && (c == '\n')) {
                            sb.append(c);
                            state = ParseState.NORMAL;
                        }
                        else {
                            if (sb.length() > 0) {
                                value = sb.toString().trim();
                                sb = new StringBuilder();
                        
                                if (key != null) {
                                    if (current_section == null) {
                                        this.setProperty(key, value);
                                    }
                                    else {
                                        this.setProperty(current_section, key, value);
                                    }
                                }
                            }
                            key = null;
                            value = null;
                        }
                        break;

                    default: 
                        sb.append(c);
                }
            }
            n = in.read(buffer, 0, bufSize);
        }
    }

    /**
     * 从字符串中载入INI信息
     * @param str
     * @param encoding
     * @throws IOException
     */
    public void load(final String str, final String encoding) throws IOException{
    	ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes(encoding));
    	load(is);
    }

    public void load(final byte[] bytes) throws IOException {
    	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
    	load(is);
    }

    /**
     * 获取属性信息
     * @param name
     * @return
     */
    public String getProperty(String name) {
        return globalProperties.getProperty(name);
    }

    /**
     * 设置属性值信息
     * @param name
     * @param value
     */
    public void setProperty(String name, String value) {
        globalProperties.setProperty(name, value);
    }

    /**
     * 迭代器
     * @return
     */
    @SuppressWarnings("unchecked")
    public Iterator<String> iterator() {
        return new IteratorFromEnumeration<String>((Enumeration<String>)globalProperties.propertyNames());
    }

    /**
     * 获取属性值
     * @param section
     * @param name
     * @return
     */
    public String getProperty(String section, String name) {
        Properties p = properties.get(section);
        return p == null ? null : p.getProperty(name);
    }

    /**
     * 设置属性值
     * @param section
     * @param name
     * @param value
     */
    public void setProperty(String section, String name, String value) {
        Properties p = properties.get(section);
        if (p == null) {
            p = new Properties();
            properties.put(section, p);
        }
        p.setProperty(name, value);
    }

    /**
     * 获取段下内容迭代器
     * @param section
     * @return
     */
    @SuppressWarnings("unchecked")
    public Iterator<String> iterator(String section) {
        Properties p = properties.get(section);
        if (p == null) {
            return null;
        }
        return new IteratorFromEnumeration<String>(
                   (Enumeration<String>)p.propertyNames());
    }

    /**
     * 段迭代器
     * @return
     */
    public Iterator<String> sections() {
        return properties.keySet().iterator();
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
        // Global properties
        Iterator<String> props = this.iterator();
        while (props.hasNext()) {
            String name = props.next();
            sb.append(name)
            .append("=")
            .append(dumpEscape(getProperty(name)))
            .append("\r\n");
        }

        // sections
        Iterator<String> sections = this.sections();
        while (sections.hasNext()) {
            String section = sections.next();
            sb.append("\r\n")
            .append("[").append(section).append("]")
            .append("\r\n");
            props = this.iterator(section);
            while (props.hasNext()) {
                String name = props.next();
                sb.append(name)
                .append("=")
                .append(dumpEscape(getProperty(section, name)))
                .append("\r\n");
            }
        }
        return sb.toString();
    }

    private static String dumpEscape(String s) {
        return s
//        		.replaceAll("\\\\", "\\\\\\\\")
//        		.replaceAll(";", "\\\\;")
//        		.replaceAll("#", "\\\\#")
//        		.replaceAll("(\r?\n|\r)", "\\\\$1")
        		;
    }

    public ByteArrayInputStream getInputStream(final String encoding) throws UnsupportedEncodingException{
    	byte[] bytes = this.toString().getBytes(encoding);
    	ByteArrayInputStream result = new ByteArrayInputStream(bytes);
    	return result;
    }

    private static class IteratorFromEnumeration<E> implements Iterator {
        private Enumeration<E> e;

        public IteratorFromEnumeration(Enumeration<E> e) {
            this.e = e;
        }

        public boolean hasNext() {
            return e.hasMoreElements();
        }

        public E next() {
            return e.nextElement();
        }

        public void remove() {
            throw new UnsupportedOperationException("Can't change underlying enumeration");
        }
    }

    public static void main(String[] args) throws Exception {
    	String fileName = "/home/jim/upload/aesm/asm.inf";
//    	String s = FileUtil.readAbsolutFile(fileName, "utf-8");
//    	System.out.println(s);
        IniUtil utils = new IniUtil();
        utils.load(new BufferedInputStream(new FileInputStream(fileName)));
        String s = utils.toString();
        IniUtil utils1 = new IniUtil();
        utils1.load(s, "utf-8");
        utils1.setProperty("file1", "url", "url123");
        System.out.println(utils1.toString());
        for(Iterator<String> it = utils1.sections(); it.hasNext();){
        	String s1 = it.next();
        	System.out.println(s1);
        }

        System.out.println("");
        for(Iterator<String> it1 = utils1.iterator("files"); it1.hasNext();){
        	String s1 = it1.next();
        	System.out.println(s1);
        }
    }
}
