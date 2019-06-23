package com.dzl.myblog.mapper;

        import org.apache.ibatis.annotations.*;
        import org.springframework.stereotype.Repository;
        import org.springframework.web.bind.annotation.RequestParam;

@Mapper
@Repository
public interface VisitorMapper {
    @Select("select visitorNum from visitor where pageName=#{pageName}")
    long getVisitorNumByPageName(@Param("pageName") String pageName);

    @Update("update visitor set visitorNum=(case pageName when 'totalVisitor' then visitorNum+1 when #{pageName} then visitorNum+1 else visitorNum end)")
    void updateVisitorNumByTotalVisitorAndPageName(@Param("pageName") String pageName);

    @Update("update visitor set visitorNum=visitorNum+1 where pageName='totalVisitor'")
    void updateVisitorNumByTotalVisitor();

    @Insert("insert into visitor(visitorNum,pageName) values(0,#{pageName})")
    void insertVisitorArticlePage(String pageName);

    @Select("select visitorNum from visitor where pageName='totalVisitor'")
    long getAllVisitor();

    @Delete("delete  from visitor where pageName='totalVisitor'")
    void deleteVisitorArtcle(@RequestParam("pageName") String pageName);
}