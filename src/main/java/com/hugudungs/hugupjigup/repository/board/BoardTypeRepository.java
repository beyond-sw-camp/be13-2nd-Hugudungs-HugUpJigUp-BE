package com.hugudungs.hugupjigup.repository.board;

import com.hugudungs.hugupjigup.data.entity.board.BoardTypeEntity;
import com.hugudungs.hugupjigup.common.enums.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BoardTypeRepository extends JpaRepository<BoardTypeEntity, Long> {
    Optional<BoardTypeEntity> findByBoardType(BoardType boardType);
}
