package data.implementations;

import data.IdGenerator;
import data.model.Node;
import data.model.Edge;

import java.util.Objects;

public class EdgeEntity implements Edge {

    private int id;
    private int from;
    private int to;
    private int length;

    public EdgeEntity(Node fromNode, Node toNode){
        id = IdGenerator.getId();
        this.from = fromNode.getId();
        this.to = toNode.getId();
        this.length = calculateLength(fromNode,toNode);
    }

    public EdgeEntity(int from, int to, int length){
        id = IdGenerator.getId();
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public EdgeEntity(int id, int from, int to, int length) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.length = length;
    }

    @Override
    public int calculateLength(Node from, Node to) {
        return (int) Math.sqrt(Math.pow((double)from.getX()-to.getX(),2)
                + Math.pow((double)from.getY()-to.getY(),2));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public EdgeEntity swapEnds(){
        return new EdgeEntity(this.to,this.from,length);
    }

    @Override
    public boolean equals(Object obj) {
        EdgeEntity objectEdge = null;
        if (obj.getClass() == EdgeEntity.class){
            objectEdge = (EdgeEntity) obj;
        }
        if (objectEdge.getTo() == to && objectEdge.getFrom() == from
                && objectEdge.length == length) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, length);
    }

    @Override
    public String toString() {
        return " ID: " + id + "\n" +
                " From ID: " + from + "\n" +
                " To ID: "+ to + "\n" +
                " Length: " + length;
    }
}
