import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

case class createChild(name:String,age:Int)
case class  Name(name:String)

class  Child extends  Actor{
  var name:String = "no name"
  def Pingo(): Unit ={
    println("Sending more love guys ")
  }

  override def postStop(): Unit = super.postStop()

  override def receive: Receive = {
    case Name(name) => {
      this.name = name
      println(s"CAlled the child actorfrom the parent actor")
    }
    case _ => println(s"Child $name got message")
  }
}

class Parent extends  Actor{
  override def receive: Receive = {
    case createChild(name:String,age:Int)=>{
      println(s"Parent about to create a child $name ....")
      val child = context.actorOf(Props[Child],name=s"$name")
     child ! Name(name)
    }
    case _=> println(s"Parent got some message .")
  }
}

class  Ping(namae:String) extends Actor{

  override def receive: Receive = {
    case Name(name)=> println(s"Builder $name $namae ")
    case _ => println(s"Bailer $namae")
  }
}

object Start  {

    def main(args: Array[String]): Unit = {
    println("My first Scala Real Project ")
     // val system = ActorSystem("kfokf")
      val actorsystem = ActorSystem()

      val pingpong = actorsystem.actorOf(Props(new Ping("Traverse")),"Pingo")
      pingpong ! Name("Juliana")

      val parent = actorsystem.actorOf(Props[Parent],name = "Mokoaea")
      parent ! createChild("Jonathan",344)

      Thread.sleep(5000)

      //look up thte childactor.
      val childJona  =actorsystem.actorSelection("/user/Parent/Jonathan")

      childJona ! PoisonPill
      println("Jonathan was killed")
      Thread.sleep(5000)

      actorsystem.terminate()




  }


}
