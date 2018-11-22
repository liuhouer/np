
package cn.northpark.query.condition;

import java.io.Serializable;

import lombok.Data;

@Data
public class NoteQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 4616425088890001237L;


    private String id;


    private String note;


    private String opened;


    private String createtime;


    private String userid;



}