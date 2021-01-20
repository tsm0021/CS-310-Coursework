// I, Trey Scott McAtee, verify that I have complied with the Academic integrity policy.
object Main {
  def main(args: Array[String]): Unit = {
     var i = 0;
     var atm = "F";
     var lizt = new Array[String](3);
     lizt(0) = "A";
     lizt(1) = "B";
     lizt(2) = "C";
     def member (atm:String, lizt:Array[String]) : Boolean = {
        
        if (atm == lizt(i)){
          return true;
        }
        else{
          i = i+1;
          if (i == 3){
            return false;
          }
          member(atm, lizt);
        }
      
      return false;
     }
    println(member(atm, lizt));
   }
}
