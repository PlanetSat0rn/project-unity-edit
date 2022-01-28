package unity.world.graph;

public class GraphTorqueNode extends GraphNode<TorqueGraph>{
    public float baseFriction, baseInertia, baseForce=0;
    public float maxTorque,maxSpeed;
    public boolean torqueProvider = false;

    public boolean torqueConsumer = false;

    public GraphTorqueNode(float friction, float inertia, GraphBuild build){
        super(build);
        baseFriction = friction;
        baseInertia = inertia;
    }

    public GraphTorqueNode(float friction, float inertia, float maxTorque, float maxSpeed, GraphBuild build){
        super(build);
        torqueProvider = true;
        baseFriction = friction;
        baseInertia = inertia;
        this.maxTorque = maxTorque;
        this.maxSpeed = maxSpeed;
    }

    public GraphTorqueNode(GraphBuild build){
        this(0.1f, 10f,build);
    }

    public float getSpeedRatio(float maxSpeed){
        if(!connector.any()){return 0;}
        return 1f-(connector.first().graph.lastVelocity/maxSpeed);
    }


    /*
    table.row().left();
    table.add("Torque system").color(Pal.accent).fillX();
    table.row().left();
    table.add("[lightgray]" + bundle.get("stat.unity.friction") + ":[] ").left();
    table.row().left();
    table.add("[lightgray]" + bundle.get("stat.unity.inertia") + ":[] ").left();
    table.add(baseInertia + "t m^2");
    setStatsExt(table);*/

    /*
    void drawPlace(int x, int y, int size, int rotation, boolean valid){
        for(int i = 0; i < connector.length; i++){
            for(int j = 0; j < connector[i].connectionPoints.length; j++){
                var cp = connector[i].connectionPoints[j];
                Lines.stroke(3.5f, Color.white);
                GraphData outPos = GraphData.getConnectSidePos(i, size, rotation);
                int dx = (outPos.toPos.x + x) * tilesize;
                int dy = (outPos.toPos.y + y) * tilesize;
                Point2 dir = Geometry.d4(outPos.dir);
                Lines.line(dx - dir.x, dy - dir.y, dx - dir.x * 2, dy - dir.y * 2);
            }
        }
    }*/
    public float getFriction(){
        return baseFriction;
    }

    public float getInertia(){
       return baseInertia;
    }

    public float getForce(){
        if(torqueProvider){
            return baseForce*maxTorque*getSpeedRatio(maxSpeed);
        }else{
            return baseForce;
        }
    }

}