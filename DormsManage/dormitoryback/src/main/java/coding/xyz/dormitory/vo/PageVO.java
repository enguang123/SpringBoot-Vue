package coding.xyz.dormitory.vo;

import lombok.Data;

@Data
public class PageVO<T> {
    private T data;
    private Long total;
}
