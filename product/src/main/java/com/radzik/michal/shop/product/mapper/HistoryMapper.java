package com.radzik.michal.shop.product.mapper;

import com.radzik.michal.shop.common.dto.ProductDto;
import com.radzik.michal.shop.product.domain.dao.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;


@Mapper(componentModel = "spring")
public interface HistoryMapper {
     @Mapping(source = "entity.id", target = "id")
     @Mapping(source = "entity.price", target = "price")
     @Mapping(source = "entity.name", target = "name")
     @Mapping(source = "entity.amount", target = "amount")
     @Mapping(source = "entity.createdDate", target = "createdDate")
     @Mapping(source = "entity.lastModifiedDate", target = "lastModifiedDate")
     @Mapping(source = "metadata.revisionType", target = "revType", qualifiedByName = "revisionTypeToString")
     @Mapping(source = "requiredRevisionNumber", target = "operationNumber")
     ProductDto toProductDto (Revision<Integer, Product> revision);

     @Named("revisionTypeToString")
     default String revisionTypeToString(RevisionMetadata.RevisionType revisionType){
          return revisionType.name();
     }
}
