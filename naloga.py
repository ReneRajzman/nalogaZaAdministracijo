# Importing libraries
import cv2
import numpy as np


# Image processing functions

# Roberts Cross filter
def my_roberts(slika):
    # Get image dimensions
    height, width = slika.shape[:2]

    # Create an empty grayscale image
    slika_robov = np.zeros((height, width), dtype=np.uint8)

    # Define the Roberts Cross kernels
    kernel_x = np.array([[1, 0], [0, -1]])
    kernel_y = np.array([[0, 1], [-1, 0]])

    # Apply the Roberts Cross filter
    for y in range(height - 1):
        for x in range(width - 1):
            # Compute the gradients using the kernels
            Gx = slika[y, x] * kernel_x[0, 0] + slika[y + 1, x + 1] * kernel_x[1, 1]
            Gy = slika[y, x + 1] * kernel_y[0, 1] + slika[y + 1, x] * kernel_y[1, 0]

            # Compute the magnitude of the gradient
            magnitude = np.sqrt(Gx ** 2 + Gy ** 2)

            # Clip the magnitude to a range of 0 to 255
            slika_robov[y, x] = np.clip(magnitude, 0, 255).astype('uint8')

    return slika_robov


# Prewitt filter
def my_prewitt(slika):
    # Get image dimensions
    height, width = slika.shape[:2]

    # Create an empty grayscale image
    slika_prewitt = np.zeros((height, width), dtype=np.uint8)

    # Define the Prewitt kernels
    kernel_x = np.array([[-1, 0, 1], [-1, 0, 1], [-1, 0, 1]])
    kernel_y = np.array([[1, 1, 1], [0, 0, 0], [-1, -1, -1]])

    # Apply the Prewitt filter
    for y in range(1, height - 1):
        for x in range(1, width - 1):
            # Compute the gradients using the kernels
            Gx = np.sum(kernel_x * slika[y - 1:y + 2, x - 1:x + 2])
            Gy = np.sum(kernel_y * slika[y - 1:y + 2, x - 1:x + 2])

            # Compute the magnitude of the gradient
            magnitude = np.sqrt(Gx ** 2 + Gy ** 2)

            # Clip the magnitude to a range of 0 to 255
            slika_prewitt[y, x] = np.clip(magnitude, 0, 255).astype('uint8')

    return slika_prewitt


# Sobel filter
def my_sobel(slika):
    # Get image dimensions
    height, width = slika.shape[:2]

    # Create an empty grayscale image
    slika_sobel = np.zeros((height, width), dtype=np.uint8)

    # Define the Sobel kernels
    kernel_x = np.array([[-1, 0, 1], [-2, 0, 2], [-1, 0, 1]])
    kernel_y = np.array([[1, 2, 1], [0, 0, 0], [-1, -2, -1]])

    # Apply the Sobel filter
    for y in range(1, height - 1):
        for x in range(1, width - 1):
            # Compute the gradients using the kernels
            Gx = np.sum(kernel_x * slika[y - 1:y + 2, x - 1:x + 2])
            Gy = np.sum(kernel_y * slika[y - 1:y + 2, x - 1:x + 2])

            # Compute the magnitude of the gradient
            magnitude = np.sqrt(Gx ** 2 + Gy ** 2)

            # Clip the magnitude to a range of 0 to 255
            slika_sobel[y, x] = np.clip(magnitude, 0, 255).astype('uint8')

    return slika_sobel


def my_canny(slika, sp_prag, zg_prag):
    slika_robov = cv2.Canny(slika, sp_prag, zg_prag)
    return slika_robov


def spremeni_kontrast(slika, alfa, beta):
    nova_slika = np.clip(alfa * slika + beta, 0, 255).astype('uint8')
    return nova_slika


def main():
    # Create a VideoCapture object to capture images from the camera
    cap = cv2.VideoCapture(0)

    # Check if the camera was opened successfully
    if not cap.isOpened():
        print("Cannot open camera")
        return

    current_configuration = '5'  # Default configuration, change to '' for regular video
    while True:
        # Read an image from the camera
        ret, frame = cap.read()

        # Check if the image was read successfully
        if not ret:
            print("Cannot read camera frame")
            return

        # Convert the frame to grayscale
        gray_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        if current_configuration == '1':
            printedFrame = my_roberts(gray_frame)
        elif current_configuration == '2':
            printedFrame = my_canny(gray_frame, 2, 20)
        elif current_configuration == '3':
            printedFrame = my_sobel(gray_frame)
        elif current_configuration == '4':
            printedFrame = my_prewitt(gray_frame)
        elif current_configuration == '5':
            printedFrame = spremeni_kontrast(frame, 5, 20)
        else:
            printedFrame = frame

        # Display the chosen frame
        cv2.imshow('frame', printedFrame)

        key = cv2.waitKey(1)
        if key == ord('q'):
            break
        elif key in [ord('1'), ord('2'), ord('3'), ord('4'), ord('5')]:
            current_configuration = chr(key)
        elif key == ord('0'):  # To reset to the regular video
            current_configuration = ''

if __name__ == '__main__':
    main()
