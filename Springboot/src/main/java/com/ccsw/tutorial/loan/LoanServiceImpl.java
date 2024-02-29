package com.ccsw.tutorial.loan;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

import jakarta.transaction.Transactional;

/**
 * @author Jaime Poveda Algueró
 *
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    GameService gameService;

    @Autowired
    ClientService clientService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Loan get(Long id) {

        return this.loanRepository.findById(id).orElse(null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Loan> find(LoanSearchDto dto) {

        LoanSpecification gameSpec = new LoanSpecification(new SearchCriteria("game.id", ":", dto.getGameId()));
        LoanSpecification clientSpec = new LoanSpecification(new SearchCriteria("client.id", ":", dto.getClientId()));
        LoanSpecification initDateSpec = new LoanSpecification(new SearchCriteria("initDate", ">=", dto.getDate()));
        LoanSpecification endDateSpec = new LoanSpecification(new SearchCriteria("endDate", "<=", dto.getDate()));

        Specification<Loan> spec = Specification.where(gameSpec).and(clientSpec).and(initDateSpec).and(endDateSpec);
        return this.loanRepository.findAll(spec, dto.getPageable().getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, LoanDto dto) throws Exception {

        Loan loan = new Loan();
        List<Loan> loans = (List<Loan>) this.loanRepository.findAll();

        // Comprobamos que la fecha de inicio del prestamo sea anterior a la final
        if (dto.getInitDate().isAfter(dto.getEndDate()))
            throw new Exception("End date before init date");

        // Comprobamos que la duración del préstamo no sea mayor a 14 días
        if (ChronoUnit.DAYS.between(dto.getInitDate(), dto.getEndDate()) >= 14)
            throw new Exception("Loan time too long");

        for (int i = 0; i < loans.size(); i++) {

            if (dto.getGame().getId().equals(loans.get(i).getGame().getId())) {

                LocalDateTime initDateG = loans.get(i).getInitDate();
                LocalDateTime endDateG = loans.get(i).getEndDate();

                if (initDateG.equals(dto.getInitDate()) || endDateG.equals(dto.getEndDate())
                        || (initDateG.isBefore(dto.getEndDate()) && endDateG.isAfter(dto.getInitDate()))) {
                    throw new Exception("Game already on loan");
                }
            }

            if (dto.getClient().getId().equals(loans.get(i).getClient().getId())) {

                LocalDateTime initDateC = loans.get(i).getInitDate();
                LocalDateTime endDateC = loans.get(i).getEndDate();

                if (initDateC.equals(dto.getInitDate()) || endDateC.equals(dto.getEndDate())
                        || (initDateC.isBefore(dto.getEndDate()) && endDateC.isAfter(dto.getInitDate()))) {
                    throw new Exception("Client with an active loan");
                }
            }
        }

        BeanUtils.copyProperties(dto, loan, "id", "game", "client");

        loan.setGame(gameService.get(dto.getGame().getId()));
        loan.setClient(clientService.get(dto.getClient().getId()));

        this.loanRepository.save(loan);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {

        return (List<Loan>) this.loanRepository.findAll();
    }
}
