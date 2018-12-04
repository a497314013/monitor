package com.rockwell.ramon.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = -1455151157L;

    public static final QMenu menu = new QMenu("menu");

    public final DateTimePath<java.time.LocalDateTime> ctime = createDateTime("ctime", java.time.LocalDateTime.class);

    public final StringPath icon = createString("icon");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath parentId = createString("parentId");

    public final StringPath perms = createString("perms");

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final StringPath url = createString("url");

    public final DateTimePath<java.time.LocalDateTime> utime = createDateTime("utime", java.time.LocalDateTime.class);

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

