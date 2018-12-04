package com.rockwell.ramon.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1454899657L;

    public static final QUser user = new QUser("user");

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    public final DateTimePath<java.time.LocalDateTime> cTime = createDateTime("cTime", java.time.LocalDateTime.class);

    public final StringPath deptId = createString("deptId");

    public final StringPath email = createString("email");

    public final StringPath fName = createString("fName");

    public final StringPath hobby = createString("hobby");

    public final StringPath id = createString("id");

    public final StringPath lName = createString("lName");

    public final StringPath memo = createString("memo");

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public final StringPath picId = createString("picId");

    public final StringPath pwd = createString("pwd");

    public final NumberPath<Long> pwdDuration = createNumber("pwdDuration", Long.class);

    public final DateTimePath<java.time.LocalDateTime> pwdExpiration = createDateTime("pwdExpiration", java.time.LocalDateTime.class);

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sex = createNumber("sex", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> uTime = createDateTime("uTime", java.time.LocalDateTime.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

