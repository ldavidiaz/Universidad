import random
class AVLNode:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.height = 1

class AVLTree:
    def __init__(self):
        self.root = None

    def height(self, node):
        if not node:
            return 0
        return node.height

    def isEmpty(self):
        return self.root is None

    def balance(self, node):
        if not node:
            return 0
        return self.height(node.left) - self.height(node.right)

    def rotate_right(self, node):
        new_root = node.left
        node.left = new_root.right
        new_root.right = node
        node.height = max(self.height(node.left), self.height(node.right)) + 1
        new_root.height = max(self.height(new_root.left), self.height(new_root.right)) + 1
        return new_root

    def rotate_left(self, node):
        new_root = node.right
        node.right = new_root.left
        new_root.left = node
        node.height = max(self.height(node.left), self.height(node.right)) + 1
        new_root.height = max(self.height(new_root.left), self.height(new_root.right)) + 1
        return new_root

    def insert(self, nodo):
        value = nodo
        def _insert(node, value):
            if not node:
                return AVLNode(value)
            elif value.id < node.value.id:
                node.left = _insert(node.left, value)
            else:
                node.right = _insert(node.right, value)
            node.height = max(self.height(node.left), self.height(node.right)) + 1
            balance = self.balance(node)
            if balance > 1 and value.id < node.left.value.id:
                return self.rotate_right(node)
            if balance < -1 and value.id > node.right.value.id:
                return self.rotate_left(node)
            if balance > 1 and value.id > node.left.value.id:
                node.left = self.rotate_left(node.left)
                return self.rotate_right(node)
            if balance < -1 and value.id < node.right.value.id:
                node.right = self.rotate_right(node.right)
                return self.rotate_left(node)
            return node
        self.root = _insert(self.root, value)

    def get_max(self, node):
      if not node:
          return None
      while node.right:
          node = node.right
      return node       

    def borrar_nodo(self, value):#value es un entero(ID)
      self.root = self._delete_node(self.root, value)

    def _delete_node(self, node, value):
        if not node:
            #print(f"No se puede eliminar el nodo con id {value}. Nodo no encontrado en el arbol AVL")
            return node
        elif value < node.value.id:
            node.left = self._delete_node(node.left, value)
        elif value > node.value.id:
            node.right = self._delete_node(node.right, value)
        else:
            if not node.left and not node.right:
                del node
                return None
            elif not node.left:
                temp = node.right
                del node
                return temp
            elif not node.right:
                temp = node.left
                del node
                return temp
            else:
                temp = self.get_max(node.left)
                node.value.id = temp.value.id
                node.left = self._delete_node(node.left, temp.value.id)
        return node

    def search(self, value):
        def _search(node, value):
            if not node:
                return None
            elif value == node.value.id:
                return node
            elif value < node.value.id:
                return _search(node.left, value)
            else:
                return _search(node.right, value)

        return _search(self.root, value)

    def get_random_node(self):
        if not self.root:
            return None
        node_list = self._get_node_list(self.root)
        return random.choice(node_list)

    def _get_node_list(self, node):
        nodes = []
        queue = [node]
        while queue:
            current_node = queue.pop(0)
            if current_node:
                nodes.append(current_node)
                queue.append(current_node.left)
                queue.append(current_node.right)
        return nodes
    '''
    def inorder(self,curr_node):
      if curr_node is not None:
        self.inorder(curr_node.left)
        print(curr_node.value, end=",")
        self.inorder(curr_node.right)

    def preorder(self,curr_node):
      if curr_node is not None:
        print(curr_node.value,end=",")        
        self.preorder(curr_node.left)
        self.preorder(curr_node.right)

    def postorder(self,curr_node):      
      if curr_node is not None:
        self.postorder(curr_node.left)
        self.postorder(curr_node.right)
        print(curr_node.value,end=",")   
    '''
    def inorder(self,curr_node,ids):
      if curr_node is not None:
        self.inorder(curr_node.left,ids)
        ids.append(curr_node.value.id)
        self.inorder(curr_node.right,ids)
      return ids

    def preorder(self,curr_node,ids):
      if curr_node is not None:
        ids.append(curr_node.value.id)       
        self.preorder(curr_node.left,ids)
        self.preorder(curr_node.right,ids)
      return ids

    def postorder(self,curr_node,ids):      
      if curr_node is not None:
        self.postorder(curr_node.left,ids)
        self.postorder(curr_node.right,ids)
        ids.append(curr_node.value.id)
      return ids

    def bfs_traversal(self):
        if not self.root:
            print()
            return []

        result = []
        queue = [self.root]

        while queue:
            node = queue.pop(0)
            result.append(node.value.id)
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        print(','.join([str(id) for id in result]))
        return result
    
    def mostrarArbol(self,tipo_recorrido):
        if not self.root:
            print()
        else:
            ids = None
            if tipo_recorrido == 1:
                    ids = self.bfs_traversal()
            elif tipo_recorrido == 2:
                    ids = self.preorder(self.root,[])
                    print(",".join([str(id) for id in ids]))
            elif tipo_recorrido == 3:
                    ids = self.inorder(self.root,[])
                    print(",".join([str(id) for id in ids]))
            elif tipo_recorrido == 4:
                    ids = self.postorder(self.root,[])
                    print(",".join([str(id) for id in ids]))