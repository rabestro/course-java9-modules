open module jmp.dto {
	exports com.epam.jmp.dto;

	requires lombok;
	requires java.desktop;
    requires jakarta.persistence;
	requires jakarta.validation;
	requires spring.data.jpa;
	requires spring.context;
	requires spring.beans;
	requires spring.boot;
    requires spring.boot.autoconfigure;
	requires spring.web;
}
