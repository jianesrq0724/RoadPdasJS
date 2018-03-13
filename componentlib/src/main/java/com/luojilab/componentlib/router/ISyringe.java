package com.luojilab.componentlib.router;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */

public interface ISyringe {
    /**
     * @param self the container itself, members to be inject into have been annotated
     *             with one annotation called Autowired
     */
    void inject(Object self);
}
