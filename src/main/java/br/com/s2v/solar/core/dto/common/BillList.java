package br.com.s2v.solar.core.dto.common;

import br.com.s2v.solar.persistence.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillList {
    private List<Bill> bills;
}
