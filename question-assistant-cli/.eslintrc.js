module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: [
    'plugin:vue/essential',
    '@vue/standard'
  ],
  parserOptions: {
    parser: 'babel-eslint'
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    "comma-dangle": ["error", "never"], //是否允许对象中出现结尾逗号
    "no-cond-assign": "error", //条件语句的条件中不允许出现赋值运算符
    "no-constant-condition": "error", //条件语句的条件中不允许出现恒定不变的量
    "no-control-regex": "error", //正则表达式中不允许出现控制字符
    "no-dupe-args": "error", //函数定义的时候不允许出现重复的参数
    "no-dupe-keys": "error", //对象中不允许出现重复的键
    "no-duplicate-case": "error", //switch语句中不允许出现重复的case标签
    "no-empty": "off", //不允许出现空的代码块
    "no-empty-character-class": "error", //正则表达式中不允许出现空的字符组
    "no-ex-assign": "error", //在try catch语句中不允许重新分配异常变量
    "no-extra-boolean-cast": "error", //不允许出现不必要的布尔值转换
    "no-extra-semi": "error", //不允许出现不必要的分号
    "no-func-assign": "error", //不允许重新分配函数声明
    "no-inner-declarations": ["error", "functions"], //不允许在嵌套代码块里声明函数
    "no-invalid-regexp": "error", //不允许在RegExp构造函数里出现无效的正则表达式
    "no-irregular-whitespace": "off", //不允许出现不规则的空格
    "no-negated-in-lhs": "error", //不允许在in表达式语句中对最左边的运算数使用取反操作
    "no-obj-calls": "error", //不允许把全局对象属性当做函数来调用
    "no-regex-spaces": "off", //正则表达式中不允许出现多个连续空格
    "no-sparse-arrays": "error", //数组中不允许出现空位置
    "no-unreachable": "error", //在return，throw，continue，break语句后不允许出现不可能到达的语句
    "use-isnan": "error", //要求检查NaN的时候使用isNaN()
    "valid-jsdoc": ["error", {
      "requireReturn": false,
      "requireParamDescription": false,
      "requireReturnDescription": false
    }], //强制JSDoc注释
    "valid-typeof": ["error", {
      "requireStringLiterals": true
    }], //在使用typeof表达式比较的时候强制使用有效的字符串
    "block-scoped-var": "error", //将变量声明放在合适的代码块里
    "complexity": "off", //限制条件语句的复杂度
    "consistent-return": "error", //无论有没有返回值都强制要求return语句返回一个值
    "curly": ["error", "all"], //强制使用花括号的风格
    "default-case": "off", //在switch语句中需要有default语句
    "dot-notation": ["warn", {"allowKeywords": true, "allowPattern": ""}], //获取对象属性的时候使用点号
    "eqeqeq": ["error", "smart"], //比较的时候使用严格等于
    "no-alert": "warn", //不允许使用alert，confirm，prompt语句
    "no-caller": "error", //不允许使用arguments.callee和arguments.caller属性
    "guard-for-in": "off", //监视for in循环，防止出现不可预料的情况
    "no-div-regex": "error", //不能使用看起来像除法的正则表达式
    "no-else-return": "off", //如果if语句有return，else里的return不用放在else里
    "no-labels": ["error", {
      "allowLoop": false,
      "allowSwitch": false
    }], //不允许标签语句
    "no-eq-null": "error", //不允许对null用==或者!=
    "no-eval": "error", //不允许使用eval()
    "no-extend-native": "error", //不允许扩展原生对象
    "no-extra-bind": "error", //不允许不必要的函数绑定
    "no-fallthrough": "error", //不允许switch按顺序全部执行所有case
    "no-floating-decimal": "error", //不允许浮点数缺失数字
    "no-implied-eval": "error", //不允许使用隐式eval()
    "no-iterator": "error", //不允许使用__iterator__属性
    "no-lone-blocks": "error", //不允许不必要的嵌套代码块
    "no-loop-func": "error", //不允许在循环语句中进行函数声明
    "no-multi-spaces": "off", //不允许出现多余的空格
    "no-multi-str": "error", //不允许用\来让字符串换行
    "no-global-assign": "error", //不允许重新分配原生对象
    "no-new": "error", //不允许new一个实例后不赋值或者不比较
    "no-new-func": "error", //不允许使用new Function
    "no-new-wrappers": "error", //不允许使用new String，Number和Boolean对象
    "no-octal": "error", //不允许使用八进制字面值
    "no-octal-escape": "error", //不允许使用八进制转义序列
    "no-param-reassign": "off", //不允许重新分配函数参数
    "no-proto": "error", //不允许使用__proto__属性
    "no-redeclare": "error", //不允许变量重复声明
    "no-return-assign": "error", //不允许在return语句中使用分配语句
    "no-script-url": "error", //不允许使用javascript:void(0)
    "no-self-compare": "error", //不允许自己和自己比较
    "no-sequences": "error", //不允许使用逗号表达式
    "no-throw-literal": "error", //不允许抛出字面量错误 throw "error"
    "no-unused-expressions": "error", //不允许无用的表达式
    "no-void": "error", //不允许void操作符
    "no-warning-comments": ["warn", {"terms": ["todo", "fixme", "any other term"]}], //不允许警告备注
    "no-with": "error", //不允许使用with语句
    "radix": "warn", //使用parseInt时强制使用基数来指定是十进制还是其他进制
    "vars-on-top": "off", //var必须放在作用域顶部
    "wrap-iife": ["error", "any"], //立即执行表达式的括号风格
    "yoda": ["error", "never", {"exceptRange": true}], //不允许在if条件中使用yoda条件
    "strict": ["error", "function"], //使用严格模式
    "no-catch-shadow": "error", //不允许try catch语句接受的err变量与外部变量重名
    "no-delete-var": "error", //不允许使用delete操作符
    "no-label-var": "error", //不允许标签和变量同名
    "no-shadow": "error", //外部作用域中的变量不能与它所包含的作用域中的变量或参数同名
    "no-shadow-restricted-names": "error", //js关键字和保留字不能作为函数名或者变量名
    "no-undef": "error", //不允许未声明的变量
    "no-undef-init": "error", //不允许初始化变量时给变量赋值undefined
    "no-undefined": "error", //不允许把undefined当做标识符使用
    "no-unused-vars": ["warn", {"vars": "all", "args": "after-used"}], //不允许有声明后未使用的变量或者参数
    "no-use-before-define": ["error", "nofunc"], //不允许在未定义之前就使用变量
    "indent": "error", //强制一致的缩进风格
    "brace-style": ["error", "1tbs", { "allowSingleLine": false}], //大括号风格
    "camelcase": ["error", {"properties": "never"}], //强制驼峰命名规则
    "comma-style": ["error", "last"], //逗号风格
    "consistent-this": ["off", "self"], //当获取当前环境的this是用一样的风格
    "eol-last": "off", //文件以换行符结束
    "func-names": "off", //函数表达式必须有名字
    "func-style": "off", //函数风格，规定只能使用函数声明或者函数表达式
    "key-spacing": ["off", {"beforeColon": false, "afterColon": true}], //对象字面量中冒号的前后空格
    "max-nested-callbacks": "off", //回调嵌套深度
    "new-cap": ["error", {"newIsCap": true, "capIsNew": false}], //构造函数名字首字母要大写
    "new-parens": "error", //new时构造函数必须有小括号
    "newline-after-var": "off", //变量声明后必须空一行
    "no-array-constructor": "error", //不允许使用数组构造器
    "no-inline-comments": "off", //不允许行内注释
    "no-lonely-if": "off", //不允许else语句内只有if语句
    "no-mixed-spaces-and-tabs": ["off", "smart-tabs"], //不允许混用tab和空格
    "no-multiple-empty-lines": ["error", {"max": 2}], //空行最多不能超过两行
    "no-nested-ternary": "error", //不允许使用嵌套的三目运算符
    "no-new-object": "error", //禁止使用new Object()
    "fun-call-spacing": "off", //函数调用时，函数名与()之间不能有空格
    "no-ternary": "off", //不允许使用三目运算符
    "no-trailing-spaces": "off", //一行最后不允许有空格
    "no-underscore-dangle": "error", //不允许标识符以下划线开头
    "no-extra-parens": "off", //不允许出现多余的括号
    "one-var": "off", //强制变量声明放在一起
    "operator-assignment": "off", //赋值运算符的风格
    "padded-blocks": ["off", "never"], //块内行首行尾是否空行
    "quote-props": "off", //对象字面量中属性名加引号
    "quotes": ["off", "single", "avoid-escape"], //引号风格
    "semi": ["off", "always"], //强制语句分号结尾
    "semi-spacing": ["off", {"before": false, "after": true}], //分后前后空格
    "sort-vars": "off", //变量声明时排序
    "space-before-blocks": ["off", "always"], //块前的空格
    "space-before-function-paren": ["off", {"anonymous": "always", "named": "never"}], //函数定义时括号前的空格
    "space-infix-ops": ["off", {"int32Hint": true}], //操作符周围的空格
    "keyword-spacing": "off", //关键字前后的空格
    "space-unary-ops": ["off", { "words": true, "nonwords": false}], //一元运算符前后不要加空格
    "wrap-regex": "error", //正则表达式字面量用括号括起来
    "no-var": "off", //使用let和const代替var
    "generator-star-spacing": ["off", "both"], //生成器函数前后空格
    "max-depth": "off", //嵌套块深度
    "max-len": "off", //一行最大长度，单位为字符
    "max-params": "off", //函数最多能有多少个参数
    "max-statements": "off", //函数内最多有几个声明
    "no-bitwise": "off", //不允许使用位运算符
    "no-plusplus": 0, //不允许使用++ --运算符
    "object-curly-spacing":"off", //对象前的空格
    "object-curly-newline":"off", //对象前的换行
    "comma-spacing":"off", //逗号前后加空格
    "spaced-comment":"off", //注释前后的空格
    "arrow-spacing":"off", //箭头前后空格
    "no-unused-components":"off" //未使用的组件
  }
}
