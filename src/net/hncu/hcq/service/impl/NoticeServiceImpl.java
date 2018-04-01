package net.hncu.hcq.service.impl;

import net.hncu.hcq.base.DaoSupportImpl;
import net.hncu.hcq.domain.Notice;
import net.hncu.hcq.service.NoticeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoticeServiceImpl extends DaoSupportImpl<Notice> implements NoticeService {

}
