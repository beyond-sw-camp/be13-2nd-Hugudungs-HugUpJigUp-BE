package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import com.hugudungs.hugupjigup.data.entity.user.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "notice")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "notice_id")),
        @AttributeOverride(name = "title", column = @Column(name = "notice_title")),
        @AttributeOverride(name = "content", column = @Column(name = "notice_content")),
        @AttributeOverride(name = "views", column = @Column(name = "notice_views"))
})
public class Notice extends BaseBoardEntity {

}