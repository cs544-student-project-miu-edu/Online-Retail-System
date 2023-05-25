package com.retail.ItemService.repository;

import com.retail.ItemService.domain.ItemLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemLineRepository extends JpaRepository<ItemLine, Integer> {
    @Query("SELECT o.lineItems FROM Order o LEFT JOIN o.lineItems l WHERE l.id=:lineID AND o.id=:orderID")
    Optional<ItemLine> findItemLineByOrder(@Param("lineID") int lineID, @Param("orderID") int orderID);
}
