from abc import ABC, abstractmethod

# abstract class
class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def display_info(self):
        pass

# encapsulation + inheritance
class Student(Person):
    def __init__(self, name, age, student_id, grade):
        super().__init__(name, age)
        self.__student_id = student_id   # private
        self.__grade = grade             # private

    # getters
    def get_student_id(self):
        return self.__student_id

    def get_grade(self):
        return self.__grade

    # setters
    def set_student_id(self, student_id):
        self.__student_id = student_id

    def set_grade(self, grade):
        self.__grade = grade

    # polymorphism
    def display_info(self):
        print(f"[Student]")
        print(f"  Name       : {self.name}")
        print(f"  Age        : {self.age}")
        print(f"  Student ID : {self.get_student_id()}")
        print(f"  Grade      : {self.get_grade()}")


# inheritance
class Teacher(Person):
    def __init__(self, name, age, employee_id, subject):
        super().__init__(name, age)
        self.employee_id = employee_id
        self.subject = subject

    # polymorphism
    def display_info(self):
        print(f"[Teacher]")
        print(f"  Name        : {self.name}")
        print(f"  Age         : {self.age}")
        print(f"  Employee ID : {self.employee_id}")
        print(f"  Subject     : {self.subject}")


# output
student1 = Student("Evan Cruz",    20, "2024-001", "A")
student2 = Student("Maria Santos", 19, "2024-002", "B+")
teacher1 = Teacher("Mr. Reyes",    35, "EMP-101",  "Computer Science")

people = [student1, student2, teacher1]

print("===== School User Information =====\n")
for person in people:
    person.display_info()
    print()