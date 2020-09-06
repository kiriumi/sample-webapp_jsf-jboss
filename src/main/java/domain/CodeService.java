package domain;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import dto.Code;
import dto.CodeExample;
import repository.CodeMapper;

@RequestScoped
public class CodeService {

    @Inject
    private CodeMapper mapper;

    public List<Code> find(final Code.Kind kind) {

        CodeExample example = new CodeExample();
        example.createCriteria().andKindEqualTo(kind.name());
        example.setOrderByClause("sort_order");

        List<Code> codes = mapper.selectByExample(example);

        return codes == null ? new ArrayList<>() : codes;
    }

}
