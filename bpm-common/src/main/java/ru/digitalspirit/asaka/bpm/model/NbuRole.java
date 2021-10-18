package ru.digitalspirit.asaka.bpm.model;

import lombok.Getter;
import lombok.Setter;
import ru.digitalspirit.asaka.applicationlist.entity.BusinessProcessRole;


@Setter
@Getter
public class NbuRole {

    private String code;

    private String prefix;

    private String suffix;

    private String managedRoleCode;

    private Boolean withDepartment;

    private Boolean withCentralSquare;

    public static NbuRole createRole(String code, String prefix, String suffix, String managedRole) {
        NbuRole role = new NbuRole();
        role.setCode(code);
        role.setPrefix(prefix);
        role.setSuffix(suffix);
        role.setManagedRoleCode(managedRole);
        return role;
    }

    public static NbuRole createRole(BusinessProcessRole bpRole) {
        NbuRole role = new NbuRole();
        role.setCode(bpRole.getCode());
        role.setPrefix(bpRole.getPrefix());
        role.setSuffix(bpRole.getSuffix());
        role.setWithDepartment(bpRole.getWithDepartment());
        role.setWithCentralSquare(bpRole.getWithCentralSquare());
        if (bpRole.getManagedRole() != null) {
            role.setManagedRoleCode(bpRole.getManagedRole().getCode());
        }
        return role;
    }

}
