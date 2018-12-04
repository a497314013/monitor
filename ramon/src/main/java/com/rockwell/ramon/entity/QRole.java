package com.rockwell.ramon.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -1454992670L;

    public static final QRole role = new QRole("role");

    public final DateTimePath<org.joda.time.DateTime> cTime = createDateTime("cTime", org.joda.time.DateTime.class);

    public final StringPath id = createString("id");

    public final ListPath<Menu, SimplePath<Menu>> menus = this.<Menu, SimplePath<Menu>>createList("menus", Menu.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath roleName = createString("roleName");

    public final StringPath roleSign = createString("roleSign");

    public final DateTimePath<org.joda.time.DateTime> uTime = createDateTime("uTime", org.joda.time.DateTime.class);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

