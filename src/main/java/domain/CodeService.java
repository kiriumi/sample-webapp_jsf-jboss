package domain;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import dto.Code;
import dto.CodeExample;
import mapper.CodeMapper;

@RequestScoped
public class CodeService {

    @Inject
    private SqlSession sqlSession;

    public List<Code> find(final Code.Kind kind) {

        CodeExample example = new CodeExample();
        example.createCriteria().andKindEqualTo(kind.name());
        example.setOrderByClause("sort_order");

        CodeMapper mapper = sqlSession.getMapper(CodeMapper.class);
        List<Code> codes = mapper.selectByExample(example);

        return codes == null ? new ArrayList<Code>() : codes;
    }
}
