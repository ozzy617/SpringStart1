package comm.mapper;

public interface Mapper <F, T> {
    T map (F object);
    default T map(F fromObject, T toOnject) {
        return toOnject;
    }
}
