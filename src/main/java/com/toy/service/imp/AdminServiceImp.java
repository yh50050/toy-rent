package com.toy.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy.model.Admin;
import com.toy.repository.AdminRepository;
import com.toy.service.AdminService;
import com.toy.utils.CheckParamUtils;
import com.toy.utils.Message;

@Transactional
@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	/**
	 * 新建一个管理员账号
	 */
	@Override
	public Message<?> save(Admin admin) {
		String adminUserName = admin.getAdminUserName();
		String adminPassword = admin.getAdminPassword();
		String adminPhone = admin.getAdminPhone();
		String adminEmail = admin.getAdminEmail();
		String adminNickname = admin.getAdminNickname();

		if (null != adminUserName && null != adminPassword && null != adminPhone && null != adminEmail) {
			if (!CheckParamUtils.checkEmail(adminUserName))
				return new Message<Admin>(HttpStatus.OK, "用户名格式不符规范", null);
			if (!CheckParamUtils.checkPasswd(adminPassword))
				return new Message<Admin>(HttpStatus.OK, "密码格式不符规范", null);
			if (!CheckParamUtils.checkPhone(adminPhone))
				return new Message<Admin>(HttpStatus.OK, "手机号格式不符规范", null);
			if (!CheckParamUtils.checkEmail(adminEmail))
				return new Message<Admin>(HttpStatus.OK, "邮箱格式不符规范", null);
			if (!CheckParamUtils.checkNickname(adminNickname))
				return new Message<Admin>(HttpStatus.OK, "昵称格式不符规范", null);

			adminRepository.save(admin);
			return new Message<Admin>(HttpStatus.OK, Message.MSG_SUCCESS, admin);
		}
		return new Message<Admin>(HttpStatus.OK, "必填字段不能为空", null);
	}

	/**
	 * 更新管理员信息（昵称、手机号、邮箱）
	 */
	@Override
	public Message<?> update(Admin admin) {
		Admin admin2 = null;
		if (null != admin) {
			admin2 = adminRepository.getOne(admin.getAdminId());
			admin2.setAdminNickname(admin.getAdminNickname());
			admin2.setAdminPhone(admin.getAdminPhone());
			admin2.setAdminEmail(admin.getAdminEmail());
			adminRepository.save(admin2);
			return new Message<Admin>(HttpStatus.OK, Message.MSG_SUCCESS, admin);
		}
		return new Message<Admin>(HttpStatus.OK, Message.MSG_FAIL, admin);
	}

	/**
	 * 根据管理员id删除一个管理员账号
	 */
	@Override
	public Message<?> delete(long adminId) {
		Message<?> message = null;
		adminRepository.delete(adminId);
		message = new Message<Admin>(HttpStatus.OK, Message.MSG_SUCCESS, null);
		return message;
	}

	/**
	 * 管理员登陆验证
	 */
	@Override
	public Message<?> login(String adminUserName, String adminPassword) {
		Admin admin = null;
		// adminPassword = CodecUtils.string2MD5(adminPassword);
		admin = adminRepository.findByAdminUserNameAndAdminPassword(adminUserName, adminPassword);
		if (null != admin) {
			return new Message<Admin>(HttpStatus.OK, Message.MSG_SUCCESS, admin);
		} else
			return new Message<Admin>(HttpStatus.OK, Message.MSG_FAIL, null);
	}

	/**
	 * 根据id得到一个管理员信息
	 */
	@Override
	public Message<?> getById(long adminId) {
		Admin admin = null;
		admin = adminRepository.getOne(adminId);
		if (null != admin) {
			admin.setAdminPassword("");
			return new Message<Admin>(HttpStatus.OK, Message.MSG_SUCCESS, admin);
		} else
			return new Message<Admin>(HttpStatus.OK, "该管理员不存在", null);
	}

	/**
	 * 修改管理员级别或是否可用
	 */
	@Override
	public Message<?> updateLevel(Admin admin) {
		if (null != admin) {
			if (null != admin.getAdminAvailable()) {
				Admin admin2 = null;
				admin2 = adminRepository.getOne(admin.getAdminId());
				if (null != admin2) {
					admin2.setAdminAvailable(admin.getAdminAvailable());
					admin2.setAdminLevel(admin.getAdminLevel());
					adminRepository.save(admin2);
					return new Message<Admin>(HttpStatus.OK, Message.MSG_SUCCESS, admin);
				}
			}
		}
		return new Message<Admin>(HttpStatus.OK, Message.MSG_FAIL, null);
	}

}
