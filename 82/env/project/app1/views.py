from django.http import HttpResponse
import csv
from .models import Student
from reportlab.pdfgen import canvas
import io
#CSV Generation Function
def export_csv(request):
    response = HttpResponse(content_type='text/csv')
    response['Content-Disposition'] = 'attachment; filename="students.csv"'
    
    writer = csv.writer(response)
    writer.writerow(['Name', 'Email', 'Enrolled At'])
    
    students = Student.objects.all() 
    for student in students:
        writer.writerow([student.name, student.email, student.enrolled_at])
    
    return response
# PDF Generation Function
def export_pdf(request):
    response = HttpResponse(content_type='application/pdf')
    response['Content-Disposition'] = 'attachment; filename="students.pdf"'
    
    buffer = io.BytesIO()
    pdf = canvas.Canvas(buffer)
    pdf.setTitle('Student List')
    
    students = Student.objects.all()
    y = 800
    for student in students:
        pdf.drawString(100, y, f"Name: {student.name}")
        pdf.drawString(100, y - 20, f"Email: {student.email}")
        pdf.drawString(100, y - 40, f"Enrolled At: {student.enrolled_at}")
        y -= 60
    
    pdf.showPage()
    pdf.save()
    
    pdf_bytes = buffer.getvalue()
    buffer.close()
    response.write(pdf_bytes)
    
    return response

