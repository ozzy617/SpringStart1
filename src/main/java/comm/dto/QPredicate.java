package comm.dto;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicate {

    private final List<Predicate> predicateList = new ArrayList<>();

    public static QPredicate builder() {
        return new QPredicate();
    }

    public <T> QPredicate add(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicateList.add(function.apply(object));
        }
        return this;
    }

    public Predicate build() {
        return Optional.ofNullable(ExpressionUtils.allOf(predicateList))
                .orElseGet(() -> Expressions.asBoolean(true).isTrue());
    }

    public Predicate buildOr() {
        return Optional.ofNullable(ExpressionUtils.anyOf(predicateList))
                .orElseGet(() -> Expressions.asBoolean(true).isTrue());
    }
}
