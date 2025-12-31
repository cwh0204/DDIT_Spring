package kr.or.ddit.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
				//기본 레이아웃
		builder.addDecoratorPath("/*", "mantis-layout.jsp")
				// 관리자용 패이지에 적용할 세컨 레이아웃
				.addDecoratorPath("/admin/*", "another-decorator.html")
				// 레리아웃을 적용하지 않을 페이지
				.addExcludedPath("/ajax/*")
				.addExcludedPath("/rest/*")
				.addExcludedPath("/resources/*");
//				.addExcludedPath("/login");
	}
}
