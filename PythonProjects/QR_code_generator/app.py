from turtle import back, fillcolor
import qrcode

def generate_qrcode(text):
    qr = qrcode.QRCode(
        version=1,
        box_size=10,
        border=4,
        error_correction=qrcode.ERROR_CORRECT_L
    )
    qr.add_data(text)
    qr.make(fit=True)
    img=qr.make_image(fill_color="black", back_color="white")
    img.save("qrimg.png")

generate_qrcode("https://www.youtube.com/watch?v=GoWbxoE4NTk")
print('Pomyslnie stworzono kod QR.')