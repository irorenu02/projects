value = 10

def modify():
    global value
    value = 25
    print("Inside function:", value)

modify()
print("Outside function:", value)