package com.hugudungs.hugupjigup.data.entity.type;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "comment_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "comment_type_id", nullable = false))
})
public class CommentType extends BaseEntity {

    @Column(name = "comment_type", nullable = false)
    private String commentType;
}
