package com.project.gelingeducation.domain.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 实体通用父类
 * </p>
 *
 * @package: com.xkcoding.orm.jpa.entity.base
 * @description: 实体通用父类
 * @author: yangkai.shen
 * @date: Created in 2018/11/7 14:01
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@MappedSuperclass
@Data
public abstract class AbstractModel implements Serializable {


}
