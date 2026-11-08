def write_data():
    with open("students.txt", "w") as f:
        f.write("Zen\n")
        f.write("Ren\n")
        f.write("Bobi\n")
        f.close()

def read_data():
    with open("students.txt", "r") as f:
        content = f.read()
        print("File contents: ")
        print(content)
        f.close()

def append_data():
    with open("students.txt", "a") as f:
        f.write("Sophia\n")
        f.write("Andi\n")
        f.close()

write_data()
read_data()

append_data()

read_data()