module jmp.cloud.service.impl {
	requires transitive jmp.service.api;
	requires jmp.dto;
	provides com.epam.engx.jmp.service.api.Service
		with com.epam.engx.jmp.cloud.service.ServiceImpl;
}
