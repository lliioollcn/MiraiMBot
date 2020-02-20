package xin.lz1998.cq.retdata;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;
import lombok.Data;

@Data
public class ApiListData<T> {
    @JSONField(name = "status")
    private String status;
    @JSONField(name = "retcode")
    private int retcode;
    @JSONField(name = "data")
    private List<T> data;
}
