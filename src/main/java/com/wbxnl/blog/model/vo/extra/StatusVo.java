package com.wbxnl.blog.model.vo.extra;

import com.wbxnl.blog.validator.group.Update;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xiaowansheng
 * @Date 2023/8/28 17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusVo {
    @NotNull(message ="ID不能为空",groups = {Update.class})
    Integer id;

    @NotNull(message ="状态不能为空",groups = {Update.class})
    Integer status;
}
