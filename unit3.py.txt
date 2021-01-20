#   Trey McAtee
#   CS 310 Unit 3
#   I, Trey McAtee, acknowledge that my work complies
#   with the academic integrity policy.

def hypercake (n, k):

    def combinations (n, r):

        def factorial (n):
            if n <= 1:
                return 1
            else:
                return n * (factorial(n - 1))
    #end of factorial

        if (0 <= r and r <= n):
            return factorial(n)/(factorial(r)*factorial(n - r))
        else:
            return 1
    #end of combinations

    sum = 0
    
    if k <= 0:
        return n
    else:
        for x in range(k+1):
            sum = sum + combinations(n, x)
        return sum

cuts = int(input("Enter the desired integer of cuts: "))
dimensions = int(input("Enter the desired integer of dimensions: "))

print(hypercake(cuts, dimensions))

