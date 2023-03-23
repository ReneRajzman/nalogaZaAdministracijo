import cv2
import numpy as np


def my_roberts(slika):
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

            slika_robov[y, x] = np.clip(magnitude, 0, 255).astype('uint8')
    return slika_robov


def my_prewitt(slika):
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

            slika_prewitt[y, x] = np.clip(magnitude, 0, 255).astype('uint8')
    return slika_prewitt



def my_sobel(slika):
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

            slika_sobel[y, x] = np.clip(magnitude, 0, 255).astype('uint8')


    return slika_sobel



def my_canny(slika, sp_prag, zg_prag):
    ##
    return slika_robov


def spremeni_kontrast(slika, alfa, beta):
    ##
    return nova_slika


def main():
    ##

if __name__ == '__main__':
    main()
