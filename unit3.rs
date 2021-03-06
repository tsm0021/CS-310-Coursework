//  Trey McAtee
//  CS 310 Unit 3
//  I, Trey McAtee, acknowledge that my work complies
//  with the academic integrity policy.

fn main(){
  let mut strCuts = String::new();
  let mut strDim = String::new();
  
  println!("Enter the desired integer of cuts: ");
  std::io::stdin().read_line(&mut strCuts);
  println!("Enter the desired integer of dimensions: ");
  std::io::stdin().read_line(&mut strDim);

  let cuts: u32 = strCuts.trim().parse().expect("integer");
  let dimensions: u32 = strDim.trim().parse().expect("integer");



  println!("{}", hypercake(cuts, dimensions));
}

fn hypercake(n: u32, k: u32) -> u32{
  fn combinations(n: u32, r: u32) -> u32{
    
    fn factorial(n: u32) -> u32{
          if n <= 1 {
              return 1;
          } else {
              return n * factorial(n - 1);
            }
    }

    if 0 <= r && r <= n{
      return factorial(n)/(factorial(r)*factorial(n - r));
    }
    else{
      return 1;
    }
  }  
let mut sum : u32 = 0;
if k <= 0{
  return n;
}
else{
  for x in 0..k+1{
    sum = sum + combinations(n, x);
  }  
  return sum;
  
}
} 
