package io.cody.r3.service;

import io.cody.r3.domain.Staff;
import io.cody.r3.model.StaffDTO;
import io.cody.r3.repos.StaffRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(final StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<StaffDTO> findAll() {
        return staffRepository.findAll()
                .stream()
                .map(staff -> mapToDTO(staff, new StaffDTO()))
                .collect(Collectors.toList());
    }

    public StaffDTO get(final Long id) {
        return staffRepository.findById(id)
                .map(staff -> mapToDTO(staff, new StaffDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final StaffDTO staffDTO) {
        final Staff staff = new Staff();
        mapToEntity(staffDTO, staff);
        return staffRepository.save(staff).getId();
    }

    public void update(final Long id, final StaffDTO staffDTO) {
        final Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(staffDTO, staff);
        staffRepository.save(staff);
    }

    public void delete(final Long id) {
        staffRepository.deleteById(id);
    }

    private StaffDTO mapToDTO(final Staff staff, final StaffDTO staffDTO) {
        staffDTO.setId(staff.getId());
        staffDTO.setFirstName(staff.getFirstName());
        staffDTO.setLastName(staff.getLastName());
        staffDTO.setAccessLevel(staff.getAccessLevel());
        staffDTO.setIsStaffActive(staff.getIsStaffActive());
        return staffDTO;
    }

    private Staff mapToEntity(final StaffDTO staffDTO, final Staff staff) {
        staff.setFirstName(staffDTO.getFirstName());
        staff.setLastName(staffDTO.getLastName());
        staff.setAccessLevel(staffDTO.getAccessLevel());
        staff.setIsStaffActive(staffDTO.getIsStaffActive());
        return staff;
    }

}
