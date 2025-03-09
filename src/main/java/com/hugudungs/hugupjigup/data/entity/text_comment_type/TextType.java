package com.hugudungs.hugupjigup.data.entity.text_comment_type;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "text_type")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "text_type_id", nullable = false))
})
public class TextType extends BaseEntity {

    @Column(name = "text_type", nullable = false)
    private String type;  // 텍스트 유형
}