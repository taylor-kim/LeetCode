x1 = n, x2 = 0, mult = 1
while n > 1:             // Why not n > 0? See Explanations below
    steal = 1
    if (n - steal) % 10 == 0: // if n % 10 == 1:
        steal += 1       // Observe: steal amount at each digit is either 1 or 2
    x1 -= steal * m
    x2 += steal * m
    n = (n - steal) / 10 // Move digits one to the right
    m *= 10              // Advance the multiplier

return [x1, x2]