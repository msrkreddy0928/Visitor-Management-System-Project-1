package com.example.VMSappdemo.Service;

import com.example.VMSappdemo.Dto.Addressdto;
import com.example.VMSappdemo.Dto.Userdto;
import com.example.VMSappdemo.Entity.Address;
import com.example.VMSappdemo.Entity.Flat;
import com.example.VMSappdemo.Entity.User;
import com.example.VMSappdemo.Enums.Role;
import com.example.VMSappdemo.Enums.UserStatus;
import com.example.VMSappdemo.Repo.FlatRepo;
import com.example.VMSappdemo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FlatRepo flatRepo;

    public Long createUser(Userdto userdto) {

        User user = User.builder()
                .name(userdto.getName())
                .email(userdto.getEmail())
                .phone(userdto.getPhone())
                .role(Role.valueOf(userdto.getRole()))
                .status(UserStatus.ACTIVE)
                .idNumber(userdto.getIdNumber())
                .build();
        if (userdto.getAddress() != null) {
            Addressdto addressdto = userdto.getAddress();
            Address address = Address.builder()
                    .line1(addressdto.getLine1())
                    .line2(addressdto.getLine2())
                    .city(addressdto.getCity())
                    .pinCode(addressdto.getPinCode())
                    .country(addressdto.getCountry())
                    .build();
            user.setAddress(address);

        }
        if (userdto.getFlatNo() != null) {
            Flat flat = flatRepo.findByNumber(userdto.getFlatNo());
            if (flat == null) {
            }
            user.setFlat(flat);
        }

        user = userRepo.save(user);

        return user.getId();
    }

}
