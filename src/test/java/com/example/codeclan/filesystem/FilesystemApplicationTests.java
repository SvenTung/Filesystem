package com.example.codeclan.filesystem;

import com.example.codeclan.filesystem.models.File;
import com.example.codeclan.filesystem.models.Folder;
import com.example.codeclan.filesystem.models.User;
import com.example.codeclan.filesystem.repositories.FileRepository;
import com.example.codeclan.filesystem.repositories.FolderRepository;
import com.example.codeclan.filesystem.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FilesystemApplicationTests {

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void canCreateAndSaveFileFolderAndUser(){
		User user = new User("Jadenxp");
		userRepository.save(user);
		Folder folder = new Folder("documents", user);
		folderRepository.save(folder);
		File file = new File("notes", "txt", 300, folder);

		folder.addFile(file);
		user.addFolder(folder);

		userRepository.save(user);
		folderRepository.save(folder);
		fileRepository.save(file);
	}

}
