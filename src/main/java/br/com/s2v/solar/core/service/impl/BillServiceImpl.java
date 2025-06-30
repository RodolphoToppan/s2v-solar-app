package br.com.s2v.solar.core.service.impl;

import br.com.s2v.solar.core.service.BillService;
import br.com.s2v.solar.persistence.repository.BillRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    //    @Override
    //    public ResponseEntity<Bill> createBill(Bill bill) {
    //        Optional<Bill> billExists = billRepository.findById(bill.getId());
    //
    //        if (billExists.isPresent()) {
    //            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    //        }
    //
    //        billRepository.save(bill);
    //        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    //    }

    //    @Override
    //    public ResponseEntity<Bill> getById(Long billId) {
    //        Optional<Bill> bill = billRepository.findById(billId);
    //
    //        if (bill.isEmpty()) {
    //            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //        }
    //
    //        return ResponseEntity.ok(bill.get());
    //    }

    //    @Override
    //    public List<Bill> getAll() {
    //        return billRepository.findAll();
    //    }
    //
    //    @Override
    //    public ResponseEntity<Void> delete(Long billId) {
    //        Optional<Bill> bill = billRepository.findById(billId);
    //
    //        if (bill.isEmpty()) {
    //            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //        }
    //
    //        billRepository.deleteById(bill.get().getId());
    //        return ResponseEntity.noContent().build();
    //    }
    //
    //    @Override
    //    public ResponseEntity<Bill> update(Long billId, Bill updatedBill) {
    //        Optional<Bill> billResult = billRepository.findById(billId);
    //
    //        if (billResult.isEmpty()) {
    //            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //        }
    //
    //        Bill bill = billResult.get();
    //        bill.setUc(updatedBill.getUc());
    //        bill.setHolder(updatedBill.getHolder());
    //        bill.getLocation().setState(updatedBill.getLocation().getState());
    //        bill.getLocation().setCity(updatedBill.getLocation().getCity());
    //        bill.getLocation().setAddress(updatedBill.getLocation().getAddress());
    //        bill.setAmount(updatedBill.getAmount());
    //        bill.setConsumption(updatedBill.getConsumption());
    //        bill.setProject(updatedBill.getProject());
    //        bill.setUpdatedAt(LocalDateTime.now());
    //
    //        billRepository.save(bill);
    //        return ResponseEntity.ok(bill);
    //    }

}
