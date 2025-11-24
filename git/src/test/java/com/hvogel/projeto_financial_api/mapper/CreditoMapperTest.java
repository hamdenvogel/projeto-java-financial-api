package com.hvogel.projeto_financial_api.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.entity.Credito;

class CreditoMapperTest {

    private final CreditoMapper mapper = Mappers.getMapper(CreditoMapper.class);

    @Test
    void testFromId() {
        assertThat(mapper.fromId(null)).isNull();

        Credito credito = mapper.fromId(1L);
        assertThat(credito).isNotNull();
        assertThat(credito.getId()).isEqualTo(1L);
    }

    @Test
    void testToDto() {
        assertThat(mapper.toDto((Credito) null)).isNull();

        Credito credito = Credito.builder().id(1L).build();
        CreditoDTO dto = mapper.toDto(credito);
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
    }

    @Test
    void testToEntity() {
        assertThat(mapper.toEntity((CreditoDTO) null)).isNull();

        CreditoDTO dto = CreditoDTO.builder().id(1L).build();
        Credito entity = mapper.toEntity(dto);
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
    }

    @Test
    void testToDtoList() {
        assertThat(mapper.toDto((java.util.List<Credito>) null)).isNull();

        java.util.List<Credito> list = java.util.List.of(Credito.builder().id(1L).build());
        java.util.List<CreditoDTO> dtoList = mapper.toDto(list);
        assertThat(dtoList).hasSize(1);
        assertThat(dtoList.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void testToEntityList() {
        assertThat(mapper.toEntity((java.util.List<CreditoDTO>) null)).isNull();

        java.util.List<CreditoDTO> list = java.util.List.of(CreditoDTO.builder().id(1L).build());
        java.util.List<Credito> entityList = mapper.toEntity(list);
        assertThat(entityList).hasSize(1);
        assertThat(entityList.get(0).getId()).isEqualTo(1L);
    }
}
