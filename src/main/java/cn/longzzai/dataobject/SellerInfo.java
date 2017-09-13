package cn.longzzai.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author longcho
 * 2017-09-10-21:32
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String Id;

    private String username;

    private String password;

    private String openid;
}
