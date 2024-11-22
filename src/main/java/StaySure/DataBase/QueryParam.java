package StaySure.DataBase;

import java.util.Date;

public class QueryParam {
    public String object_class;
    public String param_string;
    public Integer param_int;
    public Long param_long;
    public Boolean param_boolean;
    public Date param_date;

    public QueryParam(String object_class, String param) {
        this.object_class = object_class;
        this.param_string = param;
    }

    public QueryParam(String object_class, int param) {
        this.object_class = object_class;
        this.param_int = param;
    }

    public QueryParam(String object_class, long param) {
        this.object_class = object_class;
        this.param_long = param;
    }

    public QueryParam(String object_class, boolean param) {
        this.object_class = object_class;
        this.param_boolean = param;
    }

    public QueryParam(String object_class, Date param) {
        this.object_class = object_class;
        this.param_date = param;
    }
}
