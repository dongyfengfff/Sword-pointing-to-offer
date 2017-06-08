package GraphProblem;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhangxin
 * Time: 2017/4/2 0002.
 * Desc: 节点的信息; 包含了节点名字,以该节点为起点的连接的节点和权重;
 */
public class Node {
    private String name;
    private Map<Node,Integer> child=new HashMap<Node,Integer>();
    public Node(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<Node, Integer> getChild() {
        return child;
    }
    public void setChild(Map<Node, Integer> child) {
        this.child = child;
    }
}
