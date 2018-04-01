package net.hncu.hcq.service;

import net.hncu.hcq.base.DaoSupport;
import net.hncu.hcq.domain.Administrator;
import net.hncu.hcq.domain.Teacher;

public interface AdminService extends DaoSupport<Administrator> {
	Administrator findByIdAndPassword(Long id, String password);
}
