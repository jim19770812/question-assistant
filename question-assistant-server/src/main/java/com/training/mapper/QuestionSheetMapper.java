package com.training.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

import lombok.Data;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import com.training.beans.QuestionSheet;

/**
 * @author hanxiaofeng
 * @date 2020-02-16 20:19:30
 * @description 
 */

@Component
public interface QuestionSheetMapper extends BaseMapper<QuestionSheet> {
    @Data
    public static class QsStatsVO {
        public Integer qs_status;
        public Long cnt;
    }

    /**
     * 获取分状态数量统计
     * @return
     */
    @Select({"select op.o_value as qs_status, ifnull(qs.cnt, 0) cnt",
             "from m_option op",
             "left join (select qs_status, count(1) cnt from question_sheet group by qs_status) qs on (op.o_value=qs.qs_status)",
             "group by op.o_value",
             "order by op.o_order"})
    List<QsStatsVO> getQuestionSheetStats();
}
