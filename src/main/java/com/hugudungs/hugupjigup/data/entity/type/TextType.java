package com.hugudungs.hugupjigup.data.entity.type;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "text_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "text_type_id", nullable = false))
})
public class TextType extends BaseEntity {

    @Column(name = "text_type", nullable = false)
    private String textType;
}