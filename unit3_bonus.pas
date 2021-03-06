//  Trey McAtee
//  CS 310 Unit 3
//  This program is designated for the bonus part of unit 3.
//  I, Trey McAtee, acknowledge that my work complies
//  with the academic integrity policy.

var sum : double; 
var cuts : int64;
var dimensions : int64;
var x : integer;

function hypercake(n, k: int64): double;
    function combinations(n, r: int64): double;
        function factorial(n: int64): int64;
            begin
                if (n <= 1) then
                    factorial := 1
                else
                    factorial := n*factorial(n-1);
            end;
        begin
            if ((0 <= r) and (r < n)) then
                begin
                    combinations:= factorial(n)/(factorial(r)*factorial(n-r));
                end
            else;
        end;
    begin
        if (k <= 0) then
            hypercake := n
        else
            for x := 0 to k do 
                sum += combinations(n, x);
            hypercake := sum;
    end;
    

begin
    writeln('Enter the desired number of cuts: ');
    readln(cuts);
    writeln('Enter the desired number of dimensions: ');
    readln(dimensions);
    writeln(hypercake(cuts, dimensions):0:0);
end.
